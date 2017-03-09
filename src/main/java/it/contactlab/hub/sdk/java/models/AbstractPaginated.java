package it.contactlab.hub.sdk.java.models;

import it.contactlab.hub.sdk.java.exceptions.ApiException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.exceptions.ServerException;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractPaginated<T> {

  public abstract List<T> elements();

  public abstract Page page();

  public abstract Function<Integer, Paginated<T>> requestFunction();

  /**
   * Retrieves the next page, if available.
   */
  public Optional<Paginated<T>> nextPage() throws HttpException, ServerException, ApiException {
    if (page().number().equals(page().totalPages())) {
      return Optional.empty();
    }

    return Optional.of(request(page().number() + 1));
  }

  /**
   * Retrieves the previous page, if available.
   */
  public Optional<Paginated<T>> previousPage() throws HttpException, ServerException, ApiException {
    if (page().number().equals(0)) {
      return Optional.empty();
    }

    return Optional.of(request(page().number() - 1));
  }

  private Paginated<T> request(Integer pageNumber) throws HttpException,
      ServerException, ApiException {
    try {
      return requestFunction().apply(pageNumber);
    } catch (RuntimeException exception) {
      if (exception.getCause() instanceof HttpException) {
        throw (HttpException) exception.getCause();
      } else if (exception.getCause() instanceof ServerException) {
        throw (ServerException) exception.getCause();
      } else if (exception.getCause() instanceof ApiException) {
        throw (ApiException) exception.getCause();
      } else {
        throw exception;
      }
    }

  }

}
