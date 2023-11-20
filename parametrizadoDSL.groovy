job('ejemplo2-job-DSL') {
	description('Job DSL de ejemplo')
  	scm {
      git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node -> 
        node / gitConfigName('jcaicedob')
        node / gitConfigEmail('certifications.jacb@gmail.com')
      }
    }
 	parameters {
  		stringParam('nombre', defaultValue = 'Jairo', description = 'Parametro de cadena')
    	choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra'])
      	booleanParam('agente', false)
  	}
  	triggers {
    	cron('H/7 * * * *')
    }
  	steps {
  		shell("bash jobscript.sh")
    }
  	publishers {
  		mailer('certifications.jacb@gmail.com',true,true)    
        slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
        }
  	}
}
