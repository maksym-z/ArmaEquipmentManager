if (!isServer) exitWith {};
_unit = _this select 0;

for '_i' from 1 to 4 do {_unit addItemToVest 'rhs_mag_30Rnd_556x45_Mk318_Stanag';};
for '_i' from 1 to 5 do {_unit addItemToVest 'rhs_mag_30Rnd_556x45_Mk262_Stanag';};
_unit addWeapon 'hlc_rifle_samr2';
_unit addPrimaryWeaponItem 'rhsusf_acc_M8541';
