[日本語のREADMEはこちらになります。](https://github.com/amata1219/KarisomeBlock/blob/master/README-ja.md)
# KarisomeBlock
Add a karisome block - a temporary block - to Minecraft.  
The karisome block will be broken after a certain amount of time and returned to the player.  
There is also a bridge mode that allows the player to place blocks in succession while guiding them with line of sight.

## How to get it
To get a karisome block, use the /karisome (amount) (duration) command.  
Simply type /karisome to get one karisome block that will break in 3 seconds.  
If you want to specify the number of blocks, specify a number from 1 to 64 in the amount argument.  
To specify how long it takes for the block to break, specify a number between 1 and 36000 in the duration argument.  
The unit of duration is tick.  
The permission of the /karisome is karisome.block.main.

## Bridge mode  
Holding karisome blocks in the main hand and pressing the F key while sneaking, blocks are continuously placed in the line of sight.  
At this time, blocks can be placed while guiding with the line of sight.  
Up to 64 blocks can be placed at once.  
It also highlights where the block will be placed next.  

## Specification
The karisome block cannot be moved by piston.  
If the player logs out before the block is returned to the player's possession, the itemized karisome block will be dropped at the player's final logout point.  
At the end of the server (when the plugin is unloaded), the block is destroyed and returned to the player's possession, regardless of the number of seconds that have elapsed since the block was placed.  
