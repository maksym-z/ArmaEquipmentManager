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
for '_i' from 1 to 2 do {_unit addItemToVest 'RH_17Rnd_9x19_g17';};
_unit addItemToVest 'SmokeShellBlue';
_unit addItemToVest 'rhs_mag_m18_purple';
_unit addWeapon 'RH_g19';
_unit addWeapon 'Binocular';
_unit linkItem 'ItemMap';
_unit linkItem 'ItemGPS';
