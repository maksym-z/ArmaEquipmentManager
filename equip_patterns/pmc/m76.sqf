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
for '_i' from 1 to 10 do {_unit addItemToVest 'rhsgref_10Rnd_792x57_m76';};
_unit addWeapon 'rhs_weap_m76';
_unit addPrimaryWeaponItem 'rhs_acc_pso1m2';
