# EcodationProject
CRUD Rest API Project



PROJE DETAYLARI

Kullanıcı kayıt işlemi
(Kaydı Kim Yaptı,Ne zaman yaptı gibi girdiler sadece database e gönderildi oradan bakabilirsiniz. )

Kayıtlı Kullanıcıları Listeleme

Id ye göre Kullanıcı Bulup Güncelleme eğer kullanıcı bulunamazsa hata mesajı fırlatma

Id ye göre Kullanıcı Bulup Silme eğer kullanıcı bulunamazsa hata mesajı fırlatma

CRUD işlemlerinin Unit Testleri Yazıldı.

H2-Console için gerekli bilgilere "application.properties" ten ulaşabilirsiniz. 

 - POSTMAN için gerekli bilgiler

#Requests

        {
                "userName":"Hüseyin",
                "userSurname":"Gürel",
                "mail":"huseyingurel@gmail.com",
                "password":"Hsynsifre"        
        }

#POST   http://localhost:8080/api/v1/user

#GET    http://localhost:8080/api/v1/users

#PUT    http://localhost:8080/api/v1/users/{id}
        
#DELETE http://localhost:8080/api/v1/users/{id}


