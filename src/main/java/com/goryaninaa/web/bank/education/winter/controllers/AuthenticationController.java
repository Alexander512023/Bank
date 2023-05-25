package com.goryaninaa.web.bank.education.winter.controllers;

import com.goryaninaa.winter.web.http.server.annotation.HttpMethod;
import com.goryaninaa.winter.web.http.server.annotation.Mapping;
import com.goryaninaa.winter.web.http.server.annotation.RequestMapping;
import com.goryaninaa.winter.web.http.server.entity.Authentication;
import com.goryaninaa.winter.web.http.server.entity.Request;
import com.goryaninaa.winter.web.http.server.request.handler.manager.Controller;

@SuppressWarnings("unused")
@RequestMapping("/authentication")
public class AuthenticationController implements Controller {

  @Mapping(value = "", httpMethod = HttpMethod.GET)
  public Authentication authenticate(final Request request) {
    final String passport = request.getHttpRequest().getParameterByName("passport").orElseThrow();
    Authentication auth = new Authentication(passport);
    auth.setSuccessful(true);
    return auth;
  }
}
