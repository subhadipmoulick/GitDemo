package Resources;

public enum APIResouces {
    addPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("maps/api/place/delete/json");
    private final String resources;
    APIResouces(String resources){
        this.resources= resources;
    }
    public String getAPIResources(){
        return this.resources;
    }

}
