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
_unit addMagazine ['rhs_30Rnd_762x39mm', 6];
_unit addWeapon 'rhs_weap_ak103_npz';
_unit addPrimaryWeaponItem 'rhs_acc_dtk';
_unit addPrimaryWeaponItem 'rhsusf_acc_eotech_xps3';
