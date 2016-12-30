package it.contactlab.hub.sdk.java.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.base.BaseProperties;
import it.contactlab.hub.sdk.java.models.base.Like;

import java.util.List;
import java.util.stream.Collectors;

public class LikeApi {

  /**
   * Add a new Like to a Customer.
   */
  public static Customer add(Auth auth, String customerId, Like like)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Like> likes = customer.base().get().likes().get();

    boolean changed = likes.add(like);

    if (changed) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().likes(likes).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Update an existing Like.
   */
  public static Customer update(Auth auth, String customerId, Like like)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Like> likes = customer.base().get().likes().get();

    List<Like> updatedLikes = likes.stream()
        .map(l -> {
          return l.id().equals(like.id()) ? like : l;
        })
        .collect(Collectors.toList());

    if (updatedLikes != likes) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().likes(updatedLikes).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Remove a tag from a Customer.
   */
  public static Customer remove(Auth auth, String customerId, String likeId)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Like> likes = customer.base().get().likes().get();

    List<Like> updatedLikes = likes.stream()
        .filter(l -> {
          return !l.id().get().equals(likeId);
        })
        .collect(Collectors.toList());

    if (updatedLikes != likes) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().likes(updatedLikes).build())
          .build());
    } else {
      return customer;
    }
  }

}
