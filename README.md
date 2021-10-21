# **Explicativo Servicio Envío de emails**
## **Utilizamos Sendgrid**
### Sendgrid es una plataforma para la gestión de correo electrónicos.
##### Para utilizar el servicio se debe crear una cuenta en SendGrid y generar una ApiKey:
##### [Clikc aquí para ir a SendGrid](https://sendgrid.com/)  

![](https://i.pinimg.com/originals/2e/90/c3/2e90c3ca9fd482cb8e1a8cc418294787.jpg)
![](https://i.pinimg.com/originals/0b/ed/96/0bed96ffa53883d433b139c39d3c6676.jpg)
 
##   **Properties**
##### En el archivo application.properties se encuentran estas dos propiedades:
##### `sendgrid.api.key=${SENDGRID_API_KEY}`
##### `sendgrid.api.from=${SENDGRID_API_FROM}` 
##### `${SENDGRID_API_KEY}`-> Valor de la Api Key generada en SendGrid.
##### `${SENDGRID_API_FROM}`-> Cargar el email de origen. 

# **Datos usuarios cargados:**

##Lista usuarios admins:
####- noah_smith@gmail.com
####- jacob_williams@gmail.com
####- emma_johnson@gmail.com
####- ava_brown@gmail.com
####- emily_jones@gmail.com
####- james_garcia@gmail.com
####- liam_miller@gmail.com
####- daniel_davis@gmail.com
####- elizabeth_rodriguez@gmail.com
####- sofia_martinez@gmail.com

##Lista usuarios regulares:
####- elijah_lopez@gmail.com
####- aiden_gonzalez@gmail.com
####- logan_wilson@gmail.com
####- grace_anderson@gmail.com
####- natalie_taylor@gmail.com
####- victoria_moore@gmail.com
####- andrew_jackson@gmail.com
####- gabriel_martin@gmail.com
####- homero_thompson@gmail.com
####- felipe_hernandez@gmail.com

###Los datos de los usuarios es lo mismo PARA TODOS. Para todos se uso el email para formar el firstname, lastname, password (name + 123), photo (src//img/name.jpg).
###EJEMPLO DE UN USUARIO:

## **ADMIN**
###Admin-1
####email = noah_smith@gmail.com
####first_name = noah
####last_name = smith
####password = noah123
####photo = src//img/noah.jpg


# **Explicativo Servicio de Amazon AWS S3**
## **¿Qué es AWS S3?**
### Amazon Simple Storage Service
#### Amazon S3 es un servicio de almacenamiento de objetos creado para almacenar y recuperar cualquier volumen de datos desde cualquier ubicación.
##### Para utilizar el servicio es necesario contar con una cuenta de AWS.
##### [Click aquí para crear una cuenta de AWS](https://aws.amazon.com/)

##### Luego debemos proceder a crear un Bucket
![](https://miro.medium.com/max/342/1*RmZj6UCz9iwK4DY6BiLP6A.png)
![](https://miro.medium.com/max/395/1*XPyBIg60uBo-_9cTLGb1LQ.png)
![](https://miro.medium.com/max/696/1*o2zJ0Vw8FO6j7aasUZwxZQ.png)

##### Posteriormente, se creará el usuario que se desee habilitar, se le otorgará acceso programático y se adjuntará la política de acceso al servicio s3
![](https://miro.medium.com/max/593/1*vWcQWrez-7bK0l98Pzuo3g.png)
![](https://miro.medium.com/max/2000/1*-z-iMMxalKIbr355wyhsJw.png)

##### Al crear el usuario correctamente se podrán visualizar el Access key ID y el Secret Access Key
##### Estos son los datos que configuraremos como variables de entorno.

##   **Properties**
##### En el archivo application.properties se encuentran estas propiedades:
##### `aws.s3.bucket=${AWS_BUCKET_NAME}` -> el nombre del bucket
##### `aws.s3.region=${AWS_REGION}` -> el código de región del bucket
##### `aws.access_key_id=${AWS_ACCESS_KEY}` -> la access key generada para el usuario
##### `aws.secret_access_key=${AWS_SECRET_KEY}` -> la secret key generada para el usuario