/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.java.backend;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author spezia
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        //packages("io.github.lgspezia.java.back.controllers");
    	//java_back/tasks-java-backend-master/src/main/java/io
        packages("io.java.back.controllers");        
    }
}
