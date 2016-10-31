import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._
import java.io.{ File, PrintWriter }

object Main extends App {
  println("Generating models from swagger.yml")
  val source = scala.io.Source.fromFile("./swagger.yml", "utf-8").getLines.mkString("\n")
  val YamlObject(definitions) = source.parseYaml.asYamlObject.getFields(YamlString("definitions"))(0).asYamlObject
  println(s"Found ${definitions.size} definitions")
  val exceptions = collection.mutable.ListBuffer[Throwable]()
  val classes = definitions.map(definitionToClass).flatten
  // println(classes.map(_._2).mkString("\n"))
  classes.foreach { case (className, content) =>
    printToFile(new File(s"src/main/java/contacthub/models/$className.java")) { p =>
      p.println(content)
    }
  }
  println(s"Generated ${classes.size} models in src/main/java/contacthub/models/")
  println(exceptions.map(_.getMessage).mkString("\n"))

  def printToFile(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try { op(p) } finally { p.close() }
  }

  def definitionToClass(definition: (YamlValue, YamlValue)): Option[(String, String)] = try {
    val (YamlString(className), fields@YamlObject(_)) = definition
    val (props, superclass) = fields.getFields(YamlString("properties")).lift(0) match {
      case Some(YamlObject(props)) => (props, None)
      case _ => fields.getFields(YamlString("allOf")).lift(0) match {
        case Some(YamlArray(allOfProps)) =>
          val YamlString(superclass) =
            allOfProps(0).asInstanceOf[YamlObject].getFields(YamlString("$ref"))(0)
          val maybeProps = util.Try {
            allOfProps(1).asInstanceOf[YamlObject]
              .getFields(YamlString("properties"))(0).asInstanceOf[YamlObject]
          }.toOption
          (maybeProps.map { case YamlObject(props) => props }.getOrElse(Map.empty),
            Some(superclass.split('/').last))
        case x =>
          throw new Exception(s"Neither 'properties' nor 'allOf' found in $className");
      }
    }
    case class Property(name: String, tpe: String, description: String)
    val properties = props.collect { case (YamlString(key), YamlObject(value)) =>
      val tpe = convertType(value)
      val YamlString(description) = value.getOrElse(YamlString("description"), YamlString("TODO"))
      Property(convertReservedKeywords(key), tpe, description)
    }.toList

    val members = properties.map { case Property(name, tpe, description) =>
      s"""|
        |  /**
        |   * $description
        |   */
        |  public final $tpe $name;""".stripMargin
    }.mkString("\n")

    val constructorParams = properties.map { case Property(name, tpe, _) =>
      s"$tpe $name"
    }.mkString(", ")

    val constructorBody = properties.map { case Property(name, tpe, _) =>
      s"    this.$name = $name;"
    }.mkString("\n")

    val constructor =
      s"""|
          |  public $className($constructorParams) {
          |$constructorBody
          |  }""".stripMargin

    val toStringBody =
      if (properties.length > 0) {
        s"""return "$className(" + ${properties.map(_.name).mkString(" + ")} + ")";"""
      } else {
        s"""return "$className()";"""
      }

    val toStringMethod =
      s"""|
          |  public String toString() {
          |    $toStringBody
          |  }
          |""".stripMargin

    val extendSegment = superclass.map(sc => s"extends $sc ").getOrElse("")

    Some(className,
      s"""| /* Automatically generated from Swagger definitions */
          | /* DO NOT EDIT MANUALLY */
          |
          |package com.contactlab.hub.models;
          |
          |import java.util.List;
          |import java.util.Map;
          |
          |public class $className $extendSegment{
          |$constructor
          |$members
          |$toStringMethod
          |}""".stripMargin)
  } catch {
    case e: Throwable => exceptions += e; None
  }

  def convertReservedKeywords(name: String): String = name match {
    case "new" => "isNew"
    case x => x
  }

  def convertType(value: Map[YamlValue, YamlValue]): String =
    value.get(YamlString("type")) match {
      case Some(YamlString(tpe)) if tpe != "array" => convertNativeType(tpe)
      case Some(YamlString(tpe)) if tpe == "array" => convertArrayType(value)
      case None => value.get(YamlString("$ref")) match {
        case Some(YamlString(ref)) => convertRefType(ref)
        case x => throw new Exception(s"unhandled type $value")
      }
    }

  def convertArrayType(value: Map[YamlValue, YamlValue]): String =
    value.get(YamlString("items")) match {
      case Some(YamlObject(arrayValue)) =>
        (arrayValue.get(YamlString("$ref")), arrayValue.get(YamlString("type"))) match {
        case (Some(YamlString(ref)), _) => s"List<${convertRefType(ref)}>"
        case (_, Some(YamlString(_))) => s"List<${convertType(arrayValue)}>"
        case x => throw new Exception(s"unhandled array type ${x}")
      }
      case _ => throw new Exception("Malformed array type: missing 'items'")
    }

  def convertNativeType(tpe: String): String = tpe match {
    case "string" => "String"
    case "boolean" => "Boolean"
    case "integer" => "Integer"
    case "object" => "Map<String, Object>" //TODO: how to handle this?
    case x => throw new Exception(s"Unknown type $x")
  }

  def convertRefType(ref: String): String =
    ref.split("/").last

  def color(cols: String*)(f: => Unit): Unit = {
    cols.foreach(print)
    val r = f
    print(Console.RESET)
    r
  }
}


