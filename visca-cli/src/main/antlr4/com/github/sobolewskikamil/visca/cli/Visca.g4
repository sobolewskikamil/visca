grammar Visca;

parse
 : destCommand
 | broadcastCommand
 ;

destCommand
 : 'Dest='dest=Number':'destCommands
 ;

broadcastCommand
 : 'Broadcast:'broadcastCommands
 ;

destCommands
 : clearAllCommand
 | setAddress
 | panTiltDownCommand
 | panTiltUpCommand
 | panTiltLeftCommand
 | panTiltRightCommand
 | panTiltHomeCommand
 | zoomTeleCommand
 | zoomWideCommand
 ;

broadcastCommands
 : autoAssign
 ;

clearAllCommand
 : 'ClearAll'
 ;

setAddress
 : 'SetAddress(address='address=Number')'
 ;

autoAssign
 : 'AutoAssign'
 ;

panTiltDownCommand
 : 'PanTiltDown(pan-speed='panspeed=Number', tilt-speed='tiltspeed=Number')'
 ;

panTiltUpCommand
 : 'PanTiltUp(pan-speed='panspeed=Number', tilt-speed='tiltspeed=Number')'
 ;

panTiltRightCommand
 : 'PanTiltRight(pan-speed='panspeed=Number', tilt-speed='tiltspeed=Number')'
 ;

panTiltLeftCommand
 : 'PanTiltLeft(pan-speed='panspeed=Number', tilt-speed='tiltspeed=Number')'
 ;

panTiltHomeCommand
 : 'PanTiltHome'
 ;

zoomTeleCommand
 : 'ZoomTele(speed='speed=Number')'
 ;

zoomWideCommand
 : 'ZoomWide(speed='speed=Number')'
 ;

Number
 : [0-9]
 ;
