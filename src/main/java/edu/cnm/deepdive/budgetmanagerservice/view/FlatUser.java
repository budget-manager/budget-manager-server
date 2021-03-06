package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User.Role;
import java.net.URI;
import java.util.Date;

/**
 *An interface that shows the top level entities and not the related entities.
 */
@JsonPropertyOrder(value = {"id", "Oauth2Key", "Username"})
public interface FlatUser {

  Long getId();

  String getOauth2Key();

  String getUsername();

  Role getRole();

  URI getHref();

}