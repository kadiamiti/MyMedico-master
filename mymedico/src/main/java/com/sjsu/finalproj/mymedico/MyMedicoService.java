package com.sjsu.finalproj.mymedico;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.sjsu.finalproj.mymedico.api.resource.*;
import com.sjsu.finalproj.mymedico.config.MyMedicoServiceConfiguration;
import com.sjsu.finalproj.mymedico.repository.UserRepository;
import com.sjsu.finalproj.mymedico.repository.UserRepositoryInterface;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;


/**
 * Hello world!
 *
 */
public class MyMedicoService  extends Service<MyMedicoServiceConfiguration>
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
        	System.out.println("Starting the MyMedico Service !");
			new MyMedicoService().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(Bootstrap<MyMedicoServiceConfiguration> bootstrap) {
		bootstrap.setName("MyMedico-Service");
		
	}

	@Override
	public void run(MyMedicoServiceConfiguration configuration, Environment environment)
			throws Exception {
		// TODO Auto-generated method stub
		environment.addResource(RootResource.class);
		
		UserRepositoryInterface userRepository = new UserRepository ();

		environment.addResource(new UserResource(userRepository));
		
	}
}
