package com.interswitchgroup.internship.samples.transaction.service.api.controller.utils;

public class RestPreconditions {
	public static <T> T checkFound(T resource) throws Exception {
        if (resource == null) {
            throw new Exception(); //MyResourceNotFoundException();
        }
        return resource;
    }
}
