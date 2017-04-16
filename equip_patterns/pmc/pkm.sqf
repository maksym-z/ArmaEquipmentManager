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
_unit addItemToVest 'rhs_100Rnd_762x54mmR_7N13';
_unit addBackpack 'BWA3_PatrolPack_Fleck';
for '_i' from 1 to 2 do {_unit addItemToBackpack 'rhs_100Rnd_762x54mmR';};
for '_i' from 1 to 2 do {_unit addItemToBackpack 'rhs_100Rnd_762x54mmR_green';};
_unit addWeapon 'rhs_weap_pkm';
