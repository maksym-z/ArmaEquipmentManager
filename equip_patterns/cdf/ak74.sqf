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
_unit addVest 'rhs_6b23_6sh92';
for '_i' from 1 to 5 do {_unit addItemToVest 'rhs_30Rnd_545x39_AK';};
for '_i' from 1 to 2 do {_unit addItemToVest 'rhs_30Rnd_545x39_AK_green';};
for '_i' from 1 to 4 do {_unit addItemToVest 'pzn_f1';};
_unit addWeapon 'rhs_pzn_weap_ak74';
_unit addPrimaryWeaponItem 'rhs_acc_dtk';
