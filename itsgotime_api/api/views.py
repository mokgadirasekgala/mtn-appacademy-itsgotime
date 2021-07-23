from rest_framework import viewsets

from api.serializers import FacilitatorSerializer
from api.models import Facilitator


class FacilitatorViewSet(viewsets.ModelViewSet):
    queryset = Facilitator.objects.all().order_by('first_name')
    serializer_class = FacilitatorSerializer
