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
_unit forceAddUniform 'TRYK_HRP_khk';
for '_i' from 1 to 2 do {_unit addItemToUniform 'hlc_30Rnd_9x19_B_MP5';};
_unit addItemToUniform 'rhs_mag_m18_purple';
_unit addVest 'TRYK_Hrp_vest_od';
_unit addItemToVest 'SmokeShellBlue';
_unit addItemToVest 'rhs_mag_m18_purple';
_unit addBackpack 'tf_rt1523g_bwmod';
_unit addHeadgear 'TRYK_H_EARMUFF';
_unit addWeapon 'hlc_smg_mp5a4';
_unit linkItem 'ItemMap';
_unit linkItem 'ItemCompass';
_unit linkItem 'ItemWatch';
_unit linkItem 'ItemRadio';
_unit linkItem 'ItemGPS';
