provider "aws" {
    region = var.region_webserver
}

resource "aws_security_group" "Webserver_SG" {
    name = "Webserver_SG"
    description = "Securty group for Webserver instance"

    ingress {
        from_port = 80
        protocol = "TCP"
        to_port = 80
        cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
        from_port = 22
        protocol = "TCP"
        to_port = 22
        cidr_blocks = ["0.0.0.0/0"] #Your IP to restrict wider access to everyone or 0.0.0.0/0 to give access for everyone which is risky and should be avoided
    }

    egress {
        from_port = 0
        protocol = "-1"
        to_port = 0
        cidr_blocks = ["0.0.0.0/0"]
    }
}

resource "aws_instance" "Webserver_Instance" {
        instance_type = var.type_webserver
        ami = var.ami_webserver
        key_name = var.key_name
        tags = {
            name = "Webserver_Instance"
        }
        security_groups = ["${aws_security_group.Webserver_SG.name}"]
        connection {
    type = "ssh"
    user = "ubuntu"
    private_key = file(var.pvt_key_name)
    host  = self.public_ip
  }


  provisioner "remote-exec" {
    inline = [
      "sudo sleep 30",
      "sudo apt-get update -y",
      "sudo apt-get install python openssh-server -y"
    ]

  }
  provisioner "local-exec" {
    command = "ansible-playbook -u ubuntu -i '${self.public_ip},' --private-key ${var.pvt_key_name} ./../../ansible-musicstoreFrontend/helloworld.yaml" 
  }

}

