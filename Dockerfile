FROM jenkins/jenkins

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false -Dpermissive-script-security.enabled=true

USER root
RUN apt-get update && \
    apt-get -y install apt-transport-https \
      ca-certificates \
      curl \
      gnupg2 \
      software-properties-common && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
      "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
      $(lsb_release -cs) \
      stable" && \
   apt-get update && \
   apt-get -y install docker-ce

# Load plugins w jenkins-plugin-cli
COPY --chown=jenkins:jenkins ./plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

# Terraform
RUN curl -fsSL https://apt.releases.hashicorp.com/gpg | apt-key add - && apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com/ $(lsb_release -cs) main" && apt-get update && apt-get install terraform

# Ansible
RUN apt-get -y install ansible

# SSHpass
RUN apt-get -y install sshpass

USER jenkins