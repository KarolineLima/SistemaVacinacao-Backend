# Manual de instalação
Esse projeto foi desenvolvido com _[Spring Boot](https://spring.io/projects/spring-boot)_ .


_Versão do JAVA : **8**_

## Configuração 

 * Ao baixar o projeto, importe em uma IDE de sua preferência como um projeto maven existente  ;
 * Realize um update do maven project para que as depêndencias necessárias definidas no pom.xml sejam configuradas  ;
 * Insira suas configurações de banco de dados e servidor de e-mail em : _src/main/resources/application.properties_  ;
 * Projeto está pronto para ser executado. Quando inicializado irá ser exibido no console :


```
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.5)
 
INFO 19029 --- [  restartedMain] b.e.i.p.vacinacao.VacinacaoApplication   : Starting VacinacaoApplication using Java 1.8.0_292 on 
INFO 19029 --- [  restartedMain] b.e.i.p.vacinacao.VacinacaoApplication   : No active profile set, falling back to default profiles: default
INFO 19029 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
INFO 19029 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
INFO 19029 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
INFO 19029 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 65 ms. Found 4 JPA repository interfaces.
INFO 19029 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
INFO 19029 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
INFO 19029 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.45]
INFO 19029 --- [  restartedMain] o.a.c.c.C.[.[.[/api-vacinacao]           : Initializing Spring embedded WebApplicationContext
INFO 19029 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2927 ms
INFO 19029 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
INFO 19029 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.30.Final
INFO 19029 --- [  restartedMain] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
INFO 19029 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
INFO 19029 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
 
 ```
 
 
### Observação:
* A partir do momento em que a aplicacação é inicializada, não é necessário reinicializar a aplicação quando realizar alterações, pois ocorre de forma automática.

