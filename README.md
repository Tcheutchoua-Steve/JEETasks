# JEETasks
A repository that contains multiple exercises with the Java EE Technology
# JEETasks
A repository that contains multiple exercises with the Java EE Technology

## Running   
import the different folders in your IDE ( preferably netbeans )

##### Glassfish
The ejbComponent is already set to be deployed on glassfish 4. 
Deploy the ejb module on glass fish 4. 
Take note of line on the server tab. The entry for the lookup is written there (i.e The properties to identify the module )  
  
On the ClientForEjbComponent app  
  Make sure the glassfishConfigurations method is being called in your class.
  right click and choose properties
  under libraries, choose add project and select the ejb module you deployed 
  Select add Jar/folder and locate the gf-client.jar file from the glassfish folder you have ( usually in $HOME/glassfishXX/glassfish/lib/gf-client.jar
  
  Save and Bingo ! you are done 
  Run the client project now.  
  
##### Jboss 4 
The ejbComponent has to be deployed on jboss before it runs  
  right click on the ejbComponent
  make the code to be compiled on Java 6 ( jboss 4 does not support java 7 or 8 )
  Change the server to be deployed on from glassfish to your jboss 4 server 
  Deploy the module on the server  
  Under the jboss server tab, take note of the line containing jndi, it has the necssary details you need for the lookup in your client application   
    
    
  On the ClientForEjbComponent app  
  right click and choose properties  
  select library and choose add project 
  select the ejbmodule you just deployed on your jboss server
  select addjar/folder and choose all the libraries in the lib folder of your jboss server
  save and run.
  you're done :) 
