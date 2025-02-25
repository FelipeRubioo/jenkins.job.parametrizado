job('parametrizadoDSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm{
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main'){ node ->
        node / gitConfigName('macloujulian')
        node / gitConfigEmail('practicasdesarrollosistemas@gmail.com')
      }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el job Booleano')
    choiceParam('planeta' , ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'] )
    booleanParam('agente', false)
  }
  triggers {
  	  cron( 'H/7 * * * *')
  }
  steps {
  	  shell("bash jobscript.sh")
  }
  publishers {
      		mailer('practicasdesarrollosistemas@gmail.com', true, true)
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
