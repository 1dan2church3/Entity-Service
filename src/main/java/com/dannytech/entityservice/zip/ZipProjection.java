package com.dannytech.entityservice.zip;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "leanZip", types = { Zip.class })
public interface ZipProjection {

	String getCode();
}
