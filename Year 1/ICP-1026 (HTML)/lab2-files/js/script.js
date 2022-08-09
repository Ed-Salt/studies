var myGallery = '{' +
	'"galleryItems" : [' +
	'{' +
		'"description" : "Brooks range mountains in Alaska",' +
		'"source" : "img/alaska"' +
	'},' +
	'{' +
		'"description" : "Arlberg pass in Austria",' +
		'"source" : "img/austria"' +
	'},' +
	'{' +
		'"description" : "Phnom kulen in Cambodia",' +
		'"source" : "img/cambodia"' +
	'},' +
	'{' +
		'"description" : "Lake pehoe in Chile",' +
		'"source" : "img/chile"' +
	'},' +
	'{' +
		'"description" : "Lake serre-poncon in France",' +
		'"source" : "img/france"' +
	'},' +
	'{' +
		'"description" : "A hot spring during sunset",' +
		'"source" : "img/hotspring"' +
	'},' +
	'{' +
		'"description" : "Civita in Italy",' +
		'"source" : "img/italy"' +
	'},' +
	'{' +
		'"description" : "Lake yamanaka in Japan",' +
		'"source" : "img/japan"' +
	'},' +
	'{' +
		'"description" : "Lake matheson in New Zealand",' +
		'"source" : "img/newzealand"' +
	'},' +
	'{' +
		'"description" : "Ko phayam in Thailand",' +
		'"source" : "img/thailand"' +
	'},' +
	'{' +
		'"description" : "Piriapolis in Uruguay",' +
		'"source" : "img/uruguay"' +
	'},' +
	'{' +
		'"description" : "Mt roraima in Venezuela",' +
		'"source" : "img/venezuela"' +
	'}' +
	']' +
'}';

var myJson = JSON.parse(myGallery);

console.log(myJson);

console.log(myJson.galleryItems.length);


//refers to existing element
var myContainer = document.getElementById("container");

for (i = 0; i < myJson.galleryItems.length; i++ )  {

//created elements
var myGallery = document.createElement("galleryItems");
var myHeader = document.createElement("header");
var myH1 = document.createElement("p");
var myPar = document.createElement("p");
var myCap = document.createElement("p");


myPar.innerHTML = "<img src=\""+myJson.galleryItems[i].source+".jpg\" title=\""+myJson.galleryItems[i].description+"\" href=\""+myJson.galleryItems[i].source+".jpg\">";
myH1.innerHTML = "<a href=\""+myJson.galleryItems[i].source+".jpg\">" + myPar.innerHTML;
myCap.innerHTML = myJson.galleryItems[i].description

myH1.appendChild(myCap);

//myGallery.appendChild(myHeader);
myGallery.appendChild(myH1);


myContainer.appendChild(myGallery);


}