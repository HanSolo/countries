## Countries

Countries is a JavaFX library which contains information about countries like their shape, gps coordinates and cities.

There are three controls you can use
- WorldPane
- RegionPane
- CountryPane

Each of these controls comes with a builder which makes it more convenient to create the objects.
And each of those controls has the ability to show a list of points of interest (Poi class) a 
heatmap as an overlay.

The Poi class has properties are: 
- latitude
- longitude
- name
- info
- valueObject
- pointSize
- fill
- stroke
- image
- svgPath
- svgPathDim

The heatmap spots are a simple list of Point objects.

<b>Attention:</b>
Keep in mind that longitude = x and latitude = y coordinate when using the spots

The central class of the library is the Country class. It has methods to get the shape(s) of the
country, it's national flag and also it's capital (if available).
The Country class is an enum with the following properties:
valueObject;
- value;
- fill;
- stroke;
- location;
- displayName;
- countryPaths;
- countryBounds;

With the library also comes a City class which contains information about it's location (lat/lon),
it's population (if available), the info if it is a capital and the country it belongs to.
There is a method getCities() in the Helper class that returns the complete list of cities (~41000).

The WorldPane simply shows all available countries which gives you the whole world.
To get a better understanding on how it works just take a look at the DemoWorldPane class.

![DemoWorldPane](https://raw.githubusercontent.com/HanSolo/countries/main/DemoWorldPane.png)

The RegionPane shows a region of countries (some of them are already predefined in the BusinessRegion class 
e.g. European Union, South America, APAC, EMEA etc.).
With this you can visualize data based on one of those regions. Just take a look at the
DemoRegionPane class to get a better understanding.

![DemoRegionPane](https://raw.githubusercontent.com/HanSolo/countries/main/DemoRegionPane.png)

The CountryPane shows just one country. To see how to use it please take a look at the 
DemoCountryPane class.

![DemoCountryPane](https://raw.githubusercontent.com/HanSolo/countries/main/DemoCountryPane.png)
