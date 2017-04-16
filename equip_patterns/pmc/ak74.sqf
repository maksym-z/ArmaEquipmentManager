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
for '_i' from 1 to 7 do {_unit addItemToVest 'rhs_30Rnd_545x39_7N10_AK';};
_unit addWeapon 'rhs_weap_aks74_2';
_unit addPrimaryWeaponItem 'rhs_acc_dtk1983';
