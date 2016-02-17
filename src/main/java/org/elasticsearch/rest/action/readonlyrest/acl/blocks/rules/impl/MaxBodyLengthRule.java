package org.elasticsearch.rest.action.readonlyrest.acl.blocks.rules.impl;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.readonlyrest.acl.blocks.rules.Rule;
import org.elasticsearch.rest.action.readonlyrest.acl.blocks.rules.RuleExitResult;
import org.elasticsearch.rest.action.readonlyrest.acl.blocks.rules.RuleNotConfiguredException;

/**
 * Created by sscarduzio on 14/02/2016.
 */
public class MaxBodyLengthRule extends Rule {
  Integer maxBodyLength;

  public MaxBodyLengthRule(Settings s) throws RuleNotConfiguredException {
    super(s);
    maxBodyLength = s.getAsInt("maxBodyLength", null);
    if (maxBodyLength == null) {
      throw new RuleNotConfiguredException();
    }
  }

  @Override
  public RuleExitResult match(RestRequest request, RestChannel channel) {
    return (request.content().length() > maxBodyLength) ? NO_MATCH : MATCH;
  }
}