if (!isServer) exitWith {};
_unit = _this select 0;

removeAllWeapons _unit;
removeAllItems _unit;
removeAllAssignedItems _unit;
removeUniform _unit;
removeVest _unit;
removeBackpack _unit;
removeHeadgear _unit;
removeGoggles _unit;
for '_i' from 1 to 2 do {_unit addItemToVest 'hlc_20rnd_762x51_b_G3';};
for '_i' from 1 to 5 do {_unit addItemToVest 'hlc_20rnd_762x51_Mk316_G3';};
_unit addWeapon 'hlc_rifle_g3sg1';
_unit addPrimaryWeaponItem 'hlc_optic_accupoint_g3';
