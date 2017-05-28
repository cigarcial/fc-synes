<section id="themes">
	<h2>Themes</h2>
		<p>
			Set your presentation theme: <br>
			<!-- Hacks to swap themes after the page has loaded. Not flexible and only intended for the reveal.js demo deck. -->
                        <a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/black.css'); return false;">Black (default)</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/white.css'); return false;">White</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/league.css'); return false;">League</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/sky.css'); return false;">Sky</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/beige.css'); return false;">Beige</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/simple.css'); return false;">Simple</a> <br>
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/serif.css'); return false;">Serif</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/blood.css'); return false;">Blood</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/night.css'); return false;">Night</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/moon.css'); return false;">Moon</a> -
			<a href="#" onclick="document.getElementById('theme').setAttribute('href','css/theme/solarized.css'); return false;">Solarized</a>
		</p>
</section>

H:

# Mastodon

Ciro Iván García López

H:

## Index

 1. Goal<!-- .element: class="fragment" data-fragment-index="1"-->
 2. Design<!-- .element: class="fragment" data-fragment-index="2"-->
 3. Interaction<!-- .element: class="fragment" data-fragment-index="3"-->
 4. Demo<!-- .element: class="fragment" data-fragment-index="4"-->
 5. Conclusions<!-- .element: class="fragment" data-fragment-index="5"-->
 6. Questions<!-- .element: class="fragment" data-fragment-index="6"-->

H:

## Goal

* Represent a Mastodon in a scene.
* Study the motion of an animal in a scene.

H:

## Design

* The following is the graph scene.
<div id='graph' >
</div>

V:

## Legs

* Is the core of the scene, all calculations are relative to the legs.
* Gives the motion to the scene.
<div id='legs_id' style="width:50%; margin:0 auto;"></div>

V: 
## Leg

* Each leg is represented by 3 points in the space.
<div id='leg_id'></div>

V:
## The motion of legs

* Synchronized motion, each leg per clock.
* Using Sine function and inverse motion with trigonometry.
<div id='motion_id' style="width:50%; margin:0 auto;"></div>

V: 
## Face and Trunk

* The face is represented by an sphere at the middle point of the frontal legs.
* The trunk is represented by an cuadratic function.
<div id='trunk_id' style="width:50%; margin:0 auto;"></div>

V: 
## Tail

* Represented by an exponential function, it begins at the middle point of the back legs.
<div id='tail_id' style="width:40%; margin:0 auto;"></div>

V:
## Body

* Represented by a sphere in the middle point of the legs.
<div id='body_id' style="width:50%; margin:0 auto;"></div>

H:

## Interaction

The sketch allows the interaction with the camera.

1. Eye Position: `$w-a-s-d-u-i$`
2. Center Position: `$up-down-left-right$`

H:

## Demo

Run the demo

H:

## Conclusions
### Results

* A first representation of a Mastodon.

V: 

## Future Work
### Current limitations

*  The calculation of vertices are complex.
*  The cosine and tangent inverse functions induce error on angles.

V:

## Future Work
### Next (possible) steps

* Make easier the vertices calculation.
* Use a lightweight represantation for the shape.
* Generalize the algorithm of motion. 
* Add textures to the representation.

H:

## Any question?

H:

## References

* [Math primer for graphics and game development](https://tfetimes.com/wp-content/uploads/2015/04/F.Dunn-I.Parberry-3D-Math-Primer-for-Graphics-and-Game-Development.pdf)
* [Fabrik Paper](http://www.andreasaristidou.com/publications/CUEDF-INFENG,%20TR-632.pdf)
