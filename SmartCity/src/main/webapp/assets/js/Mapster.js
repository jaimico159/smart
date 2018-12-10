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
    console.log("PONEIDO POSICION PTO");
    if(this.position == null){
      this.position = new Coordinate(lat,long);
    }
    else {
      this.position.lat = lat;
      this.position.lng = long;
    }
    if(this.gMarker != null){
      console.log("MARKER NO NULL");
      this.gMarker.setPosition({lat: lat, lng: long});
      console.log(gMarker);
    }
    console.log(this.position);
  }
  setGoogleMarker(){
    this.gMarker = new google.maps.Marker({
      icon: this.iconurl,
      position: {lat: this.position.lat, lng: this.position.long},
      map: this.map,
      visible: true
    });
    console.log("<<<<<<<<<<<<<<<");
    console.log(this.gMarker.getPosition().lat());
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
    this.id = null;
    this.map = null;
    this.name = null;
    this.gPolygon = null;
  }
  setId(id){
    this.id = id;
  }
  setMap(map){
    this.map = map;
  }
  setName(name){
    this.name = name;
  }
  build(arreglo){
  	let camino = [];
  	arreglo.forEach((element, index)=>{
  	  camino[index] = {lat: element[1], lng: element[0]};
  	});
  	this.gPolygon = new google.maps.Polygon({
  		paths: camino
  	});
  	this.gPolygon.setMap(this.map);
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
  	let points = this.map.getBounds();
  	console.log("Llamando a la funcion /getPolygons para llenar el arreglo de Polygons");
    this.polygons.forEach((element)=>{
      element.gPolygon.setMap(null);
    });
  	this.polygons.length = 0;
      this.cars.length = 0;
  	let info = {
  	  "longSupDerecha": points.getSouthWest().lng(),
  	  "latSupDerecha": points.getNorthEast().lat(),
  	  "longSupIzquieda": points	.getNorthEast().lng(),
  	  "latSupIzquieda": points.getNorthEast().lat(),
  	  "longInfDerecha": points.getSouthWest().lng(),
  	  "latInfDerecha": points.getNorthEast().lat(),
  	  "longInfIzquierda": points.getNorthEast().lng(),
  	  "latInfIzquierda": points.getSouthWest().lat()
  	};
  	fetch('/getPolygons',{
  		method: 'POST',
  		body: JSON.stringify(info), // data can be `string` or {object}!
  		headers:{
  		  'Content-Type': 'application/json; charset=utf-8'
  		}					
  	}).then(response => {
  		  if(response.ok) {
  			  return response.json()
  		  } else {
  			 console.log("ERROR");
  		    throw "Error en la llamada Ajax";
  		  }
  	  }).then(data => {
  	  // Work with JSON data here
  		  data.polygons.map( (element) => {
  		    let pol = new Polygon();
  		    pol.setId(element.id);
  		    pol.setName(element.name);
  		    pol.setMap(this.map);
  		    pol.build(element.path);
  		    this.polygons.push(pol);
  		  });
  	  }).catch(err => {
  	  console.log("ERROR");
  	});
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
    this.points_of_interest = new Array();
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
    let mapita = this;
    google.maps.event.addListenerOnce(mapita.map, 'bounds_changed', () => {
        console.log(mapita.map);
        mapita.realtime.setMap(mapita.map);
        mapita.historymode.setMap(mapita.map);
        mapita.setConsole();
        console.log("ASIGNANDO");
        mapita.setInterestPoints();
        console.log("TERMINANDO D ASIGNAR");
    });
    
    this.dragendId = this.map.addListener('dragend', ()=>{
      this.setInterestPoints();
    });
    this.zoomChangedId = this.map.addListener('zoom_changed', ()=>{
      this.setInterestPoints();
    });

  }
  addInterestPoint(pointObject){
    let point = new PointOfInterest();
    point.setId(pointObject.id);
    point.setMap(this.map);
    point.setTitle(pointObject.name);
    point.setIconUrl("https://img.icons8.com/ios/50/000000/golang-filled.png");
    console.log(pointObject.position[1]);
    console.log(pointObject.position[0]);
    point.setPosition(pointObject.position[1], pointObject.position[0]);
    point.setGoogleMarker();
    point.setInfoWindow(pointObject.description);
    console.log(point);
    this.points_of_interest.push(point);
    
  }
  setInterestPoints(){
    let points = this.map.getBounds();
    this.points_of_interest.forEach((element)=>{
      element.gMarker.setMap(null);
    });
    this.points_of_interest.length = 0;
    console.log("Llamando a la funcion /getInterestPoints para llenar el arreglo de Puntos de Interes");
    let info = {
      "longSupDerecha": points.getSouthWest().lng(),
      "latSupDerecha": points.getNorthEast().lat(),
      "longSupIzquieda": points .getNorthEast().lng(),
      "latSupIzquieda": points.getNorthEast().lat(),
      "longInfDerecha": points.getSouthWest().lng(),
      "latInfDerecha": points.getNorthEast().lat(),
      "longInfIzquierda": points.getNorthEast().lng(),
      "latInfIzquierda": points.getSouthWest().lat()
    };
    fetch('/getPointsOfInterest',{
      method: 'POST',
      body: JSON.stringify(info), // data can be `string` or {object}!
      headers:{
        'Content-Type': 'application/json; charset=utf-8'
      }         
    }).then(response => {
      console.log("FETCHING");
        if(response.ok) {
          console.log("EVERITHING WENT GOOD")
             return response.json()
         } else {
           console.log("ERROR");
             throw "Error en la llamada Ajax";
         }
        console.log("FINISHED");
      }).then(data => {
      // Work with JSON data here
        data.points.forEach( (element) => {
          console.log(element);
          this.addInterestPoint(element);
        });
        console.log(data);
      }).catch(err => {
      console.log("ERROR");
    });
  }
  RenderMap(){
  	google.maps.event.addListenerOnce(this.map, 'bounds_changed', () => {
  	console.log("Llamando render");
      if(this.state === 1){
        this.historymode.stopHistory();
        this.realtime.initRealTime();
      }
      else if(this.state === 2){
        this.realtime.stopLoop();
        this.historymode.initHistory();
      }
  	});
  }
}