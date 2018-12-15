function holamundo(){
  console.log("Hi causa");
}
class Coordinate {
  constructor(lat, lng){
    this.lat = lat;
    this.lng = lng;
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
  setPosition(lat, lng){
    if(this.position == null){
      this.position = new Coordinate(lat,lng);
    }
    else {
      this.position.lat = lat;
      this.position.lng = lng;
    }
    if(this.gMarker != null){
      this.gMarker.setPosition({lat: lat, lng: lng}); 
    }
  }
  setGoogleMarker(){
    this.gMarker = new google.maps.Marker({
      icon: this.iconurl,
      position: {lat: this.position.lat, lng: this.position.lng},
      map: this.map,
      visible: true
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
	super();
    this.gMarkers = new Array();
  }
  build(history){
	console.log(history);
	history.forEach((position) => {
	  
		let marker = new google.maps.Marker({
	    icon: this.iconurl,
	    map: this.map,
	    position: {lat: position[0], lng: position[1]},
	    visible: true
	  });
	  marker.addListener('click', () => {
        if (marker.getAnimation() !== null) {
          this.gMarkers.forEach((marker) => {
            marker.setAnimation(null);
          });
	    } else {
	      this.gMarkers.forEach((marker) => {
	        marker.setAnimation(google.maps.Animation.BOUNCE);
	      });
	    }
	  });
	  this.gMarkers.push(marker);
	});
  }
  show(){
    this.gMarkers.forEach( (marker) => {
      marker.setVisible(true);
    });
  }
  hide(){
    this.gMarkers.forEach( (marker) => {
      marker.setVisible(false);
    });
  }
  destroy(){
	this.id = null;
	this.map = null;
	this.title = null;
	this.iconurl = null;
	this.gMarkers.forEach((marker)=>{
	  marker.setMap(null);
	});
  }
  
}

class RealTimeCar extends Car {
  constructor(){
	super();
    this.gMarker = null;
    this.infoWindow = null;
    this.position = null;
  }
  setPosition(lat, lng){
    if(this.position == null){
      this.position = new Coordinate(lat,lng);
    }
    else {
      this.position.lat = lat;
      this.position.lng = lng;
    }
    if(this.gMarker){
      this.gMarker.setPosition({lat: lat, lng: lng});
    }
  }
  setGoogleMarker(){
    this.gMarker = new google.maps.Marker({
      icon: this.iconurl,
      map: this.map,
      position: {lat: this.position.lat, lng: this.position.lng},
      visible: true,
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
  	  camino[index] = {lat: element[0], lng: element[1]};
  	});
  	this.gPolygon = new google.maps.Polygon({
  		paths: camino,
  		strokeColor: '#9977FB',
  		stokeOpacity: 0.4,
  		fillColor: '#9977FB',
  		fillOpacity: 0.1,
  	});
  	this.gPolygon.setMap(this.map);
  }
  
  show(){
	  this.gPolygon.setMap(this.map);
  }
  hide(){
	  this.gPolygon.setMap(null);
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
	this.cars.forEach((element)=>{
      element.gMarker.setMap(null);
    });
    this.cars.length = 0;
    console.log("Llamando a la funcion /getCars para llenar el arreglo de Polygons");
    fetch('/getRealTimeCars',{
  		method: 'POST',
  		headers:{
  		  'Content-Type': 'application/json; charset=utf-8'
  		}					
  	}).then(response => {
  		  if(response.ok) {
  			  return response.json();
  		  } else {
  			 console.log("ERROR EN AJAX");
  		    throw "Error en la llamada Ajax";
  		  }
  	  }).then(data => {
  	  // Work with JSON data here
  		  data.cars.forEach( (element) => {
  			let car = new RealTimeCar();
  		    car.setId(element.id);
  		    car.setMap(this.map);
  		    car.setTitle(element.description);
  		    car.setIconUrl("./assets/icons/car2.png");
  		    car.setPosition(element.position[0], element.position[1]);
  		    car.setGoogleMarker();
	  	    car.setInfoWindow();
  		    
	  	    this.cars.push(car);
	  	  });
  	  }).catch(err => {
  	  console.log(err);
  	  throw err;
  	});
  }
  refreshData(){
    this.setPolygons();
    this.setCars();
  }
  initLoop(){
    let that = this;
    this.loopId = setInterval(() => {that.setCars()}, 10000);
    this.dragendId = this.map.addListener('dragend', () => {
      that.refreshData();
    });
    this.zoomChangedId = this.map.addListener('zoom_changed', () => {
      that.refreshData();
    });
  }
  stopLoop(){
	this.cars.forEach((car) => {
		car.hide();
	});
	this.polygons.forEach((polygon) => {
		polygon.hide();
	});
    clearInterval(this.loopId);
    google.maps.event.removeListener(this.dragendId);
    google.maps.event.removeListener(this.zoomChangedId);
    
  }
  initRealTime(){
    this.refreshData();
    this.initLoop();
  }
  stopRealTime(){
    this.stopLoop();
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
	this.cars.forEach((car)=>{
      car.destroy();
    });
    this.cars.length = 0;
    console.log("Llamando a la funcion /getCars para llenar el historico");
    fetch('/getHistory',{
  		method: 'POST',
  		headers:{
  		  'Content-Type': 'application/json; charset=utf-8'
  		}					
  	}).then(response => {
  		  if(response.ok) {
  			  return response.json();
  		  } else {
  			 console.log("ERROR EN AJAX");
  		    throw "Error en la llamada Ajax";
  		  }
  	  }).then(data => {
  	  // Work with JSON data here
  		  data.cars.forEach( (element) => {
  			let car = new HistoryCar();
  		    car.setId(element.id);
  		    car.setMap(this.map);
  		    car.setTitle(element.id);
  		    car.setIconUrl("./assets/icons/car2.png");
	  	    car.build(element.history);
	  	    
	  	    this.cars.push(car);
	  	  });
  	  }).catch(err => {
  	  console.log(err);
  	  throw err;
  	});
  }
  refreshData(){
    this.setCars();
  }
  initHistory(){
    let that = this;
    that.refreshData();
    this.dragendId = this.map.addListener('dragend', () => {
      that.refreshData();
    });
    this.zoomChangedId = this.map.addListener('zoom_changed', () => {
      that.refreshData();
    });
  }
  stopHistory(){
    this.cars.forEach((car)=>{
    	car.hide();
    });
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
      if(this.switch.value === "History"){
        this.switch.value = "Real Time";
        this.switch.innerHTML = "Real Time";
        this.state = 1;
        mapsterInstance.state = 1;
        mapsterInstance.historymode.stopHistory();
        mapsterInstance.realtime.initRealTime();
      }
      else if(this.switch.value === "Real Time"){
        this.switch.value = "History";
        this.switch.innerHTML = "History";
        this.state = 2;
        mapsterInstance.state = 2;
        mapsterInstance.realtime.stopRealTime();
        mapsterInstance.historymode.initHistory();
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
    point.setIconUrl("./assets/icons/point_of_interest.svg");
    console.log(pointObject.position[1]);
    console.log(pointObject.position[0]);
    point.setPosition(pointObject.position[0], pointObject.position[1]);
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
        this.realtime.stopRealTime();
        this.historymode.initHistory();
      }
  	});
  }
}