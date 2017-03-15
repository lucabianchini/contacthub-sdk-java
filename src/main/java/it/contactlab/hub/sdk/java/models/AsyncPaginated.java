package it.contactlab.hub.sdk.java.models;

import it.contactlab.hub.sdk.java.exceptions.ApiException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.exceptions.ServerException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class AsyncPaginated<T> {

  private final Paged<T> pagedElements;
  private final Function<Integer, CompletionStage<AsyncPaginated<T>>> requestFunction;

  public AsyncPaginated(
      Paged<T> pagedElements,
      Function<Integer, CompletionStage<AsyncPaginated<T>>> requestFunction
  ) {
    this.pagedElements = pagedElements;
    this.requestFunction = requestFunction;
  }

  public List<T> elements() {
    return pagedElements.elements();
  }

  public Page page() {
    return pagedElements.page();
  }

  /**
   * Retrieves the next page, if available.
   */
  public Optional<CompletionStage<AsyncPaginated<T>>> nextPage() {
    if (page().number().equals(page().totalPages())) {
      return Optional.empty();
    }

    return Optional.of(request(page().number() + 1));
  }

  /**
   * Retrieves the previous page, if available.
   */
  public Optional<CompletionStage<AsyncPaginated<T>>> previousPage() {
    if (page().number().equals(0)) {
      return Optional.empty();
    }

    return Optional.of(request(page().number() - 1));
  }

  private CompletionStage<AsyncPaginated<T>> request(Integer pageNumber) {
    return requestFunction.apply(pageNumber);
  }

  @Override
  public String toString() {
    return "AsyncPaginated{"
      + "page=" + page()
      + ", elements=" + elements()
      + "}";
  }

}
