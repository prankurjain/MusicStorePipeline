variable "region_webserver" { default = "us-east-2" }
variable "type_webserver" { default = "t2.micro" }
variable "ami_webserver" { default = "ami-00399ec92321828f5" }
variable "key_name" { default = "terraform_vm" }
variable "pvt_key_name" {
  default = "/root/.ssh/mykey.pem"
}

