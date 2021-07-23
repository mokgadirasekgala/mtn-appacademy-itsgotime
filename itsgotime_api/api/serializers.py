from rest_framework import serializers
from api.models import Facilitator


class FacilitatorSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Facilitator
        fields = ('first_name', 'last_name', 'role', 'profile_pic')
