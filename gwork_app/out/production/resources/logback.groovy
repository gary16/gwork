import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.net.*
import ch.qos.logback.core.*
import ch.qos.logback.core.encoder.*
import ch.qos.logback.core.read.*
import ch.qos.logback.core.rolling.*
import ch.qos.logback.core.status.*



statusListener(OnConsoleStatusListener)

def props = new Properties()
props.load(this.getClass().getClassLoader().getResourceAsStream("properties/application.properties"))

def config = new ConfigSlurper().parse(props)

def env =  System.properties['spring.profiles.active'] ?: 'production'
def appenderList = []
def level = INFO

def ip = '127.0.0.1'
def appName = config.app.name
def instanceName =  System.properties['app.instance.name'] ?: appName
def LOG_RECEIVER_DIR = '/apps/logs/log_receiver/'+appName+'/logs/' + instanceName+'/'+ip
def LOG_MERCURY_DIR = '/apps/logs/trace/logs/' + instanceName+'/'+ip
def LOG_HERMES_DIR = '/apps/logs/hermes/logs/' + instanceName+'/'+ip

jmxConfigurator()

if (env == 'production') {
	appenderList.add("ROLLING")
} else if(env == 'integratetest') {
	appenderList.add("ROLLING")
	level = INFO
} else if(env == 'development') {
	appenderList.add("CONSOLE")
	level = DEBUG
}

	appenderList.add("CONSOLE")
	level = DEBUG
	appender("CONSOLE", ConsoleAppender) {
		encoder(PatternLayoutEncoder) { pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" }
	}	

	
    //logger("org.springframework.core.io.support.PathMatchingResourcePatternResolver", OFF, [], false)
    
    //logger("org.springframework.beans.factory.support.DefaultListableBeanFactory", OFF, [], false)
    
    //logger("org.springframework.context.annotation.ClassPathBeanDefinitionScanner", OFF, [], false)
	
    //logger("org.springframework.beans.factory.support.DisposableBeanAdapter", OFF, [], false)




root(level, appenderList)
