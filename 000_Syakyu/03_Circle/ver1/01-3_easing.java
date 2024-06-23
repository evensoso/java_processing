<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Shader Park</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://openprocessing.org/openprocessing_sketch.js"></script>
    <link rel="stylesheet" href="./style.css">
  </head>  
  <body>
    <canvas class="my-canvas"></canvas>		
    <script type="module">
      import {sculptToMinimalRenderer} from 'https://unpkg.com/shader-park-core/dist/shader-park-core.esm.js';
      import {shaderParkCode} from './shaderParkCode.js';
			
			let state = {
        buttonHover: 0.0,
        currButtonHover: 0.0,
        click: 0.0,
        currClick: 0.0
      };
			
      let canvas = document.querySelector('.my-canvas');
      canvas.addEventListener('mouseover', () => state.buttonHover = 5, false);
      canvas.addEventListener('mouseout', () => state.buttonHover = 0.0, false);
      canvas.addEventListener('mousedown', () => state.click = 1.0, false);
      canvas.addEventListener('mouseup', () => state.click = 0.0, false);
      
      // This converts your Shader Park code into a shader and renders it to the my-canvas element
      sculptToMinimalRenderer(canvas, shaderParkCode, () => {
        state.currButtonHover = state.currButtonHover*.999 + state.buttonHover*.001;
        state.currClick = state.currClick*.97 + state.click*.03;
        return {
          'buttonHover' : state.currButtonHover,
          'click' : state.currClick,
					'_scale': 1.5
        };
      });
			
    </script>
  </body>
</html>