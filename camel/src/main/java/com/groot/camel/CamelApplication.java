package com.groot.camel;

import org.apache.camel.CamelContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/camel-context.xml")
public class CamelApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class);
    }

    public void run(String... args) throws Exception {
//        final CamelContext ctx = new DefaultCamelContext();
//        //add route
//        ctx.addRoutes(new RestRouter("restRouteId"));
//        ctx.start();
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            public void run() {
//                try {
//                    ctx.stop();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"clear camel context"));
    }


    /**
     * 删除route
     * @param ctx
     * @param routeId
     * @throws Exception
     */
    public void remove(CamelContext ctx, String routeId) throws Exception {
        ctx.removeRoute(routeId);
    }
}
