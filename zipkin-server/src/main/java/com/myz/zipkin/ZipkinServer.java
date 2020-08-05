package com.myz.zipkin;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin2.server.internal.EnableZipkinServer;
import zipkin2.server.internal.ZipkinActuatorImporter;
import zipkin2.server.internal.ZipkinModuleImporter;
import zipkin2.server.internal.banner.ZipkinBanner;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableZipkinServer
public class ZipkinServer {
  static {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(ZipkinServer.class)
      .banner(new ZipkinBanner())
      .initializers(new ZipkinModuleImporter(), new ZipkinActuatorImporter())
      .properties(
        EnableAutoConfiguration.ENABLED_OVERRIDE_PROPERTY + "=false",
        "spring.config.name=zipkin-server").run(args);
  }
}