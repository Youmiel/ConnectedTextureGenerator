# ConnectedTextureGenerator
A java program for creating connected glass texture for [Youmiel/ConnectedGlass1.14](https://github.com/Youmiel/ConnectedGlass1.14).<p>
You can use it to create connected glass texture for other resourcepacks(e.g. programmer-art resourcepack).
## Usage
1. Clone this repository (it is a Maven project).
2. Move glass textures into ./resources/input/ folder (now support 16x16 textures only).
3. Compile and run.
4. Outputs are in ./resources/output/ folder, move .png files in ./resources/output/ct to [your_resourcepack_name]/assets/minecraft/textures/block/ctm/, and move .mcmeta files in ./resources/output/json to [your_resourcepack_name]/assets/minecraft/textures/block/ or somewhere you reference the textures in block models. You can also change the code to meet your requirements.
