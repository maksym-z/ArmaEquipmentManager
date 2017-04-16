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
for '_i' from 1 to 8 do {_unit addItemToVest 'rhs_10Rnd_762x54mmR_7N1';};
for '_i' from 1 to 2 do {_unit addItemToVest 'rhs_mag_zarya2';};
for '_i' from 1 to 2 do {_unit addItemToVest 'SmokeShell';};
for '_i' from 1 to 2 do {_unit addItemToVest 'RH_8Rnd_9x18_Mak';};
_unit addWeapon 'rhs_weap_svdp';
_unit addWeapon 'RH_mak';
_unit addPrimaryWeaponItem 'rhs_acc_pso1m2';
