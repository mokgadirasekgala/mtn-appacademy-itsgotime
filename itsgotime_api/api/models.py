from django.db import models


class Facilitator(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100, null=True, blank=True)
    role = models.CharField(max_length=100, null=True, blank=True)
    profile_pic = models.ImageField(upload_to='photos', max_length=254, null=True)

    def __str__(self):
        return f"{self.first_name} {self.last_name}"