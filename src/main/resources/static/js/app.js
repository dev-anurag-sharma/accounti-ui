// Custom JavaScript for Accounti

$(document).ready(function() {
    // Initialize theme from localStorage or default to light
    const currentTheme = localStorage.getItem('theme') || 'light';
    setTheme(currentTheme);
    
    // Theme Toggle
    $('#themeToggle').on('click', function() {
        const body = document.body;
        const currentTheme = body.getAttribute('data-bs-theme');
        const newTheme = currentTheme === 'light' ? 'dark' : 'light';
        setTheme(newTheme);
        localStorage.setItem('theme', newTheme);
    });
    
    function setTheme(theme) {
        document.body.setAttribute('data-bs-theme', theme);
        const icon = $('#themeIcon');
        if (theme === 'dark') {
            icon.removeClass('bi-moon-stars').addClass('bi-sun');
        } else {
            icon.removeClass('bi-sun').addClass('bi-moon-stars');
        }
    }
    
    // Auto-dismiss alerts after 5 seconds
    setTimeout(function() {
        $('.alert:not(.alert-permanent)').fadeOut('slow');
    }, 5000);
    
    // Confirm delete actions
    $('.btn-delete').on('click', function(e) {
        if (!confirm('Are you sure you want to delete this item?')) {
            e.preventDefault();
        }
    });
    
    // Number formatting
    $('.format-currency').each(function() {
        const value = parseFloat($(this).text());
        if (!isNaN(value)) {
            $(this).text(formatCurrency(value));
        }
    });
    
    function formatCurrency(value) {
        return new Intl.NumberFormat('en-IN', {
            style: 'currency',
            currency: 'INR',
            minimumFractionDigits: 2
        }).format(value);
    }
    
    // Table row click handler
    $('.table-clickable tbody tr').on('click', function() {
        const url = $(this).data('href');
        if (url) {
            window.location.href = url;
        }
    });
    
    // Print functionality
    $('.btn-print').on('click', function() {
        window.print();
    });
    
    // Export to CSV (simple implementation)
    $('.btn-export-csv').on('click', function() {
        const table = $(this).closest('.card').find('table');
        if (table.length) {
            const csv = tableToCSV(table);
            downloadCSV(csv, 'export.csv');
        }
    });
    
    function tableToCSV(table) {
        const rows = table.find('tr');
        const csv = [];
        
        rows.each(function() {
            const cols = $(this).find('td, th');
            const row = [];
            cols.each(function() {
                row.push('"' + $(this).text().trim().replace(/"/g, '""') + '"');
            });
            csv.push(row.join(','));
        });
        
        return csv.join('\n');
    }
    
    function downloadCSV(csv, filename) {
        const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
        const link = document.createElement('a');
        if (link.download !== undefined) {
            const url = URL.createObjectURL(blob);
            link.setAttribute('href', url);
            link.setAttribute('download', filename);
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
    
    // Form validation
    $('form.needs-validation').on('submit', function(e) {
        if (!this.checkValidity()) {
            e.preventDefault();
            e.stopPropagation();
        }
        $(this).addClass('was-validated');
    });
    
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Initialize popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
    
    // Sidebar menu state persistence
    $('.sidebar .nav-link[data-bs-toggle="collapse"]').on('click', function() {
        const target = $(this).attr('href');
        const isExpanded = $(target).hasClass('show');
        localStorage.setItem('sidebar-' + target, !isExpanded);
    });
    
    // Restore sidebar menu state
    $('.sidebar .collapse').each(function() {
        const id = '#' + $(this).attr('id');
        const isExpanded = localStorage.getItem('sidebar-' + id) === 'true';
        if (isExpanded) {
            $(this).addClass('show');
        }
    });
});
