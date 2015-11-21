#pragma rs java_package_name(com.droidarehere.rsintcompare)
#pragma version(1)

#pragma rs_fp_relaxed

rs_allocation alloc_b;
rs_allocation alloc_diffs;
int num_diffs;

void RS_KERNEL compare_ints(int a, uint32_t x) {
    int b = rsGetElementAt_int(alloc_b, x);
    if (a != b) {
        rsAtomicInc(&num_diffs);
    }
}

void get_num_diffs() {
    rsSetElementAt_int(alloc_diffs, num_diffs, 0);
}
