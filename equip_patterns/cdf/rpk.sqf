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
for '_i' from 1 to 4 do {_unit addItemToVest 'rhs_45Rnd_545X39_7N22_AK';};
for '_i' from 1 to 2 do {_unit addItemToVest 'rhs_45Rnd_545X39_AK_Green';};
_unit addWeapon 'rhs_pzn_weap_rpk74n';
_unit addItemToUniform 'pzn_vil_1pn34';
