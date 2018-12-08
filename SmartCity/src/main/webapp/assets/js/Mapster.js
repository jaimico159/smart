function holamundo(){
  console.log("Hi causa");
}
class Coordinate {
  constructor(lat, long){
    this.lat = lat;
    this.long = long;
  }
}
class InfoWindow {
  constructor(content, map, gMarker){
    this.content = content;
    this.map = map;
    this.gMarker = gMarker;
    console.log("Empezaondo");
    this.gInfoWindow = new google.maps.InfoWindow({
      content: this.content,
      maxWidth: 650,
    });
    console.log("Terminando");
    this.Listener(this.gMarker, this.map, this.gInfoWindow);
  }

  setContent(content){
		this.content = content;
		this.gInfoWindow.setContent(this.content);
	}
	show(){
		this.gInfoWindow.open(this.map, this.gMarker);
	}
	hide(){
		this.gInfoWindow.close();
	}
  Listener(marker, map, infoW){
		this.gMarker.addListener('click', function(){
			infoW.open(map, marker);
		});
  } 
}

class DataJson {
	constructor(map, options, url){
		this.map = map;
		this.options = options;
		this.url = url;
		this.data = new google.maps.Data();
		this.data.loadGeoJson(this.url);
		this.data.setStyle(this.options);
	}


	show(){
		this.data.setMap(this.map);
	}
	hide(){
		this.data.setMap(null);
	}
	setStyle(options){
		this.data.setStyle(options);
	}

}

class PointOfInterest {
  constructor(){
    this.id = null;
    this.map = null;
    this.title = null;
    this.iconurl = null;
    this.gMarker = null;
    this.position = null;
    this.infoWindow = null;
  }
  setId(id){
    this.id = id;
  }
  setMap(map){
    this.map = map;
  }
  setTitle(title){
    this.title = title;
  }
  setIconUrl(iconurl){
    this.iconurl = iconurl;
  }
  setPosition(lat, long){
    if(this.position){
      this.position = new Coordinate(lat,long);
    }
    else {
      this.position.lat = lat;
      this.position.long = long;
    }
    if(this.gMarker){
      this.gMarker.setPosition({lat: lat, lng: long});
    }
  }
  setGoogleMarker(){
    this.gMarker = new google.maps.Marker({
      icon: this.iconurl,
      map: this.map,
      position: {lat: this.position.lat, lng: this.position.long},
      visible: false,
    });
  }
  setInfoWindow(content){
    this.infoWindow = new InfoWindow(content, this.map, this.gMarker);
  }
  show(){
    this.gMarker.setVisible(true);
  }
  hide(){
    this.gMarker.setVisible(false);
    this.infoWindow.hide();
  }
}

class Car {
  constructor(){
    this.id = null;
    this.map = null;
    this.title = null;
    this.iconurl = null;
    // this.gMarker = null;
    // this.position = null;
    // this.infoWindow = null;
  }
  setId(id){
    this.id = id;
  }
  setMap(map){
    this.map = map;
  }
  setTitle(title){
    this.title = title;
  }
  setIconUrl(iconurl){
    this.iconurl = iconurl;
  }
  show(){
    console.log("Showing Car");
  }
  hide(){
    console.log("Hiding Car");
  }
}

class HistoryCar extends Car {
  constructor(){
    this.gMarkers = new Array();
    this.jsonHistory = null;
  }
  setHistory(positions){
    let marker = new google.maps.Marker({
      icon: this.iconurl,
      map: this.map,
      position: {lat: this.position.lat, lng: this.position.long},
      visible: false,
    });
    this.gMarkers.push(marker);
    marker.addListener('click', () => {
      if (marker.getAnimation() !== null) {
        marker.setAnimation(null);
      } else {
        marker.setAnimation(google.maps.Animation.BOUNCE);
      }
    });
  }
  show(){
    this.gMarkers.map( (marker) => {
      marker.setVisible(true);
    })
  }
  hide(){
    this.gMarkers.map( (marker) => {
      marker.setVisible(false);
    })
  }
  
}

class RealTimeCar extends Car {
  constructor(){
    this.gMarker = null;
    this.position = null;
    this.infoWindow = null;
  }
  setPosition(lat, long){
    if(this.position){
      this.position = new Coordinate(lat,long);
    }
    else {
      this.position.lat = lat;
      this.position.long = long;
    }
    if(this.gMarker){
      this.gMarker.setPosition({lat: lat, lng: long});
    }
  }
  setGoogleMarker(){
    this.gMarker = new google.maps.Marker({
      icon: this.iconurl,
      map: this.map,
      position: {lat: this.position.lat, lng: this.position.long},
      visible: false,
    });
  }
  setInfoWindow(content){
    this.infoWindow = new InfoWindow(content, this.map, this.gMarker);
  }
  show(){
    this.gMarker.setVisible(true);
  }
  hide(){
    this.gMarker.setVisible(false);
    this.infoWindow.hide();
  }
}

class Polygon {
  constructor(){
    this.id = id;
    this.element = element;
    this.map = map;
    this.name = name;
  }
  setId(id){
    this.id = id;
  }
  setElement(element){
    this.element = element;
  }
  setMap(map){
    this.map = map;
  }
  setName(name){
    this.name = name;
  }
  setGeoJson(){
    this.geoJson = new DataJson();
  }
}

class RealTimeMode {
  constructor() {
    this.map = null;
    this.polygons = new Array();
    this.cars = new Array();
    this.loopId = null;
    this.dragendId = null;
    this.zoomChangedId = null;
  }
  setMap(map){
    this.map = map;
  }
  setPolygons(){
    this.polygons.length = 0;
    this.cars.length = 0;
    console.log("Llamando a la funcion /getPolygons para llenar el arreglo de Polygons");
  }
  setCars(){
    //realtime cars
    console.log("Llamando a la funcion /getCars para llenar el arreglo de Polygons");
  }
  refreshData(){
    this.setPolygons();
    this.setCars();
  }
  initLoop(){
    let that = this;
    this.loopId = setInterval(() => {that.refreshData()}, 10000);
    this.dragendId = this.map.addListener('dragend', () => {
      that.refreshData();
    });
    this.zoomChangedId = this.map.addListener('zoom_changed', () => {
      that.refreshData();
    });
  }
  stopLoop(){
    clearInterval(this.loopId);
    google.maps.event.removeListener(this.dragendId);
    google.maps.event.removeListener(this.zoomChangedId);
  }
  initRealTime(){
    this.refreshData();
    this.initLoop();
  }
  stopRealTime(){
    this.stopLoop;
  }
}

class HistoryMode {
  constructor(){
    this.map = null;
    this.cars = new Array();
    this.dragendId = null;
    this.zoomChangedId = null;
  }
  setMap(map){
    this.map = map;
  }
  setCars(){
    this.cars.length = 0;
    //historycar
    console.log("Llamando a la funcion /getCars para llenar el arreglo de Polygons");
  }
  refreshData(){
    this.setCars();
  }
  initHistory(){
    let that = this;
    this.dragendId = this.map.addListener('dragend', () => {
      that.refreshData();
    });
    this.zoomChangedId = this.map.addListener('zoom_changed', () => {
      that.refreshData();
    });
  }
  stopHistory(){
    google.maps.event.removeListener(this.dragendId);
    google.maps.event.removeListener(this.zoomChangedId);
  }
}
class Console {
  constructor() {
    this.switch = null;
    this.state = null;
    this.fromdate = null;
    this.todate = null;
    this.mapster = null;
  }
  setMapsterInstance(mapster){
    this.mapster = mapster;
  }
  setState(state){
    this.state = state;
  }
  setFromDate(fromdate){
    this.fromdate = document.getElementById(fromdate);
  }
  setToDate(todate){
    this.todate = document.getElementById(todate);
  }
  setSwitch(button){
    this.switch = document.getElementById(button);
    let mapsterInstance = this.mapster;
    console.log("ENRADNO");
    this.switch.addEventListener('click', () => {
      console.log("clockeando");
      console.log(this);
      if(this.switch.value === "History"){
        this.switch.value = "Real Time";
        this.switch.innerHTML = "Real Time";
        mapsterInstance.RenderMap();
        this.state = 2;
        mapsterInstance.state = 2;
      }
      else if(this.switch.value === "Real Time"){
        this.switch.value = "History";
        this.switch.innerHTML = "History";
        mapsterInstance.RenderMap();
        this.state = 1;
        mapsterInstance.state = 1;
      }
    });
  }
}

/*
* States {initial: 0,realtime: 1, history: 2}
*
*
*/
class Mapster {
  constructor(){
    this.state = 1;
    this.element = null;
    this.consola = new Console();
    this.map = null;
    this.points_of_interest = new Array;
    this.historymode = new HistoryMode();
    this.realtime = new RealTimeMode();
    this.dragendId = null;
    this.zoomChangedId = null;
  }
  setConsole(){
    this.consola.setMapsterInstance(this);
    this.consola.setState(this.state);
    this.consola.setFromDate("fromdate");
    this.consola.setToDate("todate");
    this.consola.setSwitch("mode");
  }
  setElement(element){
    this.element = document.getElementById(element);
  }
  setOptions(options){
    this.options = options;
  }
  setMap(){
    this.map = new google.maps.Map(this.element, this.options);
    this.map.mapTypes.set('principal', mapStyle('Principal', maptypeprincipal));
    this.map.mapTypes.set('plateado', mapStyle('Plateado', maptypesilver));
    this.map.mapTypes.set('retro', mapStyle('Retro', maptyperetro));
    this.map.mapTypes.set('dark', mapStyle('Oscuro', maptypedark));
    this.map.mapTypes.set('night', mapStyle('Noche', maptypenight));
    this.map.mapTypes.set('aubergine', mapStyle('Aubergine', maptypeaubergine));
    this.map.setMapTypeId('principal');
    this.realtime.setMap(this.map);
    this.historymode.setMap(this.map);
    this.setConsole();
    this.setInterestPoints();
    let refresh = this.setInterestPoints;
    this.dragendId = this.map.addListener('dragend', ()=>{
      refresh();
    });
    this.zoomChangedId = this.map.addListener('zoom_changed', ()=>{
      refresh();
    });

  }
  addInterestPoint(jsonPoint){
    let point = new PointOfInterest();
    point.setId(jsonPoint.id);
    point.map(this.map);
    point.setTitle(jsonPoint.title);
    point.setIconUrl("icon.svg");
    point.setPosition(jsonPoint.position[0], jsonPoint.position[1]);
    point.setGoogleMarker();
    point.setInfoWindow("CONTENIDO");
    this.points_of_interest.push(point);
  }
  setInterestPoints(){
    this.points_of_interest.length = 0;
    console.log("Llamando a la funcion /getInterestPoints para llenar el arreglo de Puntos de Interes");
  }
  RenderMap(){
    if(this.state === 1){
      this.historymode.stopHistory();
      this.realtime.initRealTime();
    }
    else if(this.state === 2){
      this.realtime.stopLoop();
      this.historymode.initHistory();
    }
  }
}