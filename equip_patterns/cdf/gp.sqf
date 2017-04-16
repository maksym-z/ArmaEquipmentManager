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
for '_i' from 1 to 3 do {_unit addItemToUniform 'rhs_mag_M441_HE';};
for '_i' from 1 to 4 do {_unit addItemToVest 'rhs_mag_m4009';};
for '_i' from 1 to 6 do {_unit addItemToVest 'rhs_mag_M433_HEDP';};
for '_i' from 1 to 4 do {_unit addItemToVest 'rhs_mag_M585_white';};
for '_i' from 1 to 3 do {_unit addItemToVest 'rhs_mag_m713_Red';};
_unit addWeapon 'rhs_weap_M320';
