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
for '_i' from 1 to 3 do {_unit addItemToVest 'rhs_mag_30Rnd_556x45_Mk318_Stanag';};
for '_i' from 1 to 4 do {_unit addItemToVest 'rhs_mag_30Rnd_556x45_M855A1_Stanag_No_Tracer';};
_unit addWeapon 'rhs_weap_m4_carryhandle';
_unit addPrimaryWeaponItem 'rhsusf_acc_anpeq15A';
_unit addPrimaryWeaponItem 'rhsusf_acc_eotech_552';
