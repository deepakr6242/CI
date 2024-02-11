pipeline {
    agent any

    stages {
        stage('Get Job Status') {
            steps {
                script {
                    def jobName = 'joba'
                    def jobStatus = getStatusOfJob(jobName)
                    echo "Status of job $jobName is: $jobStatus"
                }
            }
        }
    }
}

def getStatusOfJob(jobName) {
    def job = Jenkins.instance.getItemByFullName(jobName)
    if (job != null) {
        def lastBuild = job.getLastBuild()
        if (lastBuild != null) {
            return lastBuild.result.toString()
        } else {
            return "No builds yet"
        }
    } else {
        return "Job not found"
    }
}
