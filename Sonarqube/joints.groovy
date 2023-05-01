def call(String stageName) {
    if ("${stageName}" == 'Build') {
        sh 'mvn clean'
        sh 'mvn install'
    }
    else if ("${stageName}" == 'CodeQualityAnalysis') {
        sh 'mvn clean'
        sh 'mvn sonar:sonar'
    }
    else if ("${stageName}" == 'BackupArtifacts') {
        sh 'mvn clean'
        sh 'mvn deploy'
    }
    else if ("${stageName}" == 'Authorisation') {
         timeout(time: 48, unit: 'HOURS') {
                    // some block
                    input message: 'Approve or Decline'
                    }
    }
}



=======================================
Jenkinsfile call the pipeline below with shared library
================================================
  
  stage ('Build') {
  steps { 
  joints ('Build')
  }
  }
  stage ('CodeQualityAnalysis') {
  steps { 
  joints ('CodeQualityAnalysis')
  }
  }
 stage ('Authorisation') {
  steps { 
  joints ('Authorisation')
  }
  }
