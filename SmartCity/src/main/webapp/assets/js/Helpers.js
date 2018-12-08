function mapStyle(nombre, jsonvar){
  let styledmap = new google.maps.StyledMapType(jsonvar,{name: nombre});
  return styledmap;
}
function initOptions(lati, lngi, zoomi, typeid, maxzoom, minzomm){
  let optionsmaps = {
    center: {
      lat:lati,
      lng:lngi
    },
    zoom:zoomi,
    MapTypeId: typeid,
    maxZoom: maxzoom,
    minZoom: minzomm,
    mapTypeControl: true,
    draggableCursor: 'url("assets/icons/cursor1.cur"), auto',
    draggingCursor: 'url("assets/icons/cursor1.cur"), addListenerToButton',
    mapTypeControlOptions: {
      style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
      position: google.maps.ControlPosition.LEFT_BOTTOM,
      mapTypeIds: ['roadmap', 'satellite', 'hybrid', 'terrain',
                'principal', 'plateado','retro','dark','night','aubergine']
    }
  };
  console.log(optionsmaps);
  return optionsmaps;
}