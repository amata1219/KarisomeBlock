[日本語のREADMEはこちらになります。](https://github.com/amata1219/KarisomeBlock/blob/master/README-ja.md)
# KarisomeBlock
Add a karisome(=temporary) block to Minecraft!  
The karisome block will break in 3 seconds and return to you.  

# How to get it
The karisome block can be gotten by executing the command /karisome.  
To specify the number of blocks, specify a number from 1 to 64 as the argument.  
For example, to get 64 blocks, type /karisome 64.  

# Specification
The permission of the /karisome is karisome.block.main.  
The karisome block cannot be moved by piston.  
If the player logs out before the block is returned to the player's possession, the itemized karisome block will be dropped at the player's final logout point.  
At the end of the server (when the plugin is unloaded), the block is destroyed and returned to the player's possession, regardless of the number of seconds that have elapsed since the block was placed.  