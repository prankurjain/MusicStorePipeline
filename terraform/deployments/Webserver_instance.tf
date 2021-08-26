provider "aws" {
    region = var.region_webserver
    shared_credentials_file = "/.aws/credentials"
    profile = "testing"
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
        from_port = 3306
        protocol = "TCP"
        to_port = 3306
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress {
        from_port = 8080
        protocol = "TCP"
        to_port = 8080
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
# resource "aws_vpc" "firstvpc" {
#   cidr_block           = "3.17.21.34"
#   enable_dns_hostnames = true

# }
# resource "aws_internet_gateway" "gw" {
#   vpc_id = aws_vpc.firstvpc.id

# }

# data "aws_eip" "my_instance_eip" {
#   id="eipalloc-03c36c903c57044f6"
# }
# resource "aws_eip_association" "my_eip_association" {
#   instance_id   = aws_instance.Webserver_Instance.id
#   allocation_id = data.aws_eip.my_instance_eip.id
# }

# resource "aws_eip" "my_instance_eip" {
#   vpc                       = true
#   instance                  = aws_instance.Webserver_Instance.id
#   associate_with_private_ip = "3.17.21.34"
#   depends_on = [
#     aws_instance.Webserver_Instance
#   ]

# }


# data "aws_eip" "my_instance_eip" {
#   public_ip ="3.17.21.34"
# }


resource "aws_instance" "Webserver_Instance" {
  instance_type = var.type_webserver
  ami           = var.ami_webserver
  key_name      = var.key_name
  tags = {
    name = "Webserver_Instance"
  }
  security_groups = ["${aws_security_group.Webserver_SG.name}"]
  connection {
    type        = "ssh"
    user        = "ubuntu"
    private_key = file(var.pvt_key_name)
    host        = self.public_ip
  }


  provisioner "remote-exec" {
    inline = [
      "sudo sleep 30",
      "sudo apt-get update -y",
      # "sudo apt-get install python openssh-server sshpass -y"
      "curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -",
      "sudo apt install nodejs",
      "sudo apt-get install python3 openssh-server -y"
    ]

  }
  provisioner "local-exec" {
    # command = "ansible-playbook -u ubuntu -i '${self.public_ip},' --private-key ${var.pvt_key_name} ./../../ansible-musicstoreFrontend/helloworld.yaml" 
     command = <<EOT
       > jenkins-ci.ini;
       echo "[jenkins-ci]"|tee -a jenkins-ci.ini;
       export ANSIBLE_HOST_KEY_CHECKING=False;
       chmod 400 ${var.pvt_key_name};
       echo "${aws_instance.Webserver_Instance.public_ip}"|tee -a jenkins-ci.ini;
       > local_url.ts;
       echo "export class LocalUrl{
	     static localurl:String='${aws_instance.Webserver_Instance.public_ip}';
       }
       "| tee -a local_url.ts;
       
       ansible-playbook --key-file=${var.pvt_key_name} -i jenkins-ci.ini -u ubuntu ./../../ansible-musicstoreFrontend/helloworld.yaml -vvv
     EOT
  }

}


# resource "aws_eip_association" "my_eip_association" {
#   instance_id   = aws_instance.Webserver_Instance.id
#   allocation_id = data.aws_eip.my_instance_eip.id
# }
